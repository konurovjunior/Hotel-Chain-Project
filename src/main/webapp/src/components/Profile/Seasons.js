import React, { useState, useEffect } from 'react';

import './Profile.css';

import CreateSeasonModal from './CreateSeasonModal';
import Season from './Season';

import { loadSeasons, cancelSeason } from '../../utils/api';

function Seasons() {
    const [seasons, setSeasons] = useState([]);
    const [showModal, setShowModal] = useState(false);

    const updateSeasons = () => {
        loadSeasons((response, status) => {
            if (status === 200) {
                setSeasons(response);
            }
            else {
                console.log(response);
            }
        });
    }

    useEffect(() => {
        updateSeasons();
    }, []);

    const onCancel = (name) => {
        cancelSeason(name, (response, status) => {
            if (status === 200) {
                updateSeasons();
            }
            else {
                console.log(response);
            }
        });
    }

    const onCreation = () => {
        setShowModal(false);
        updateSeasons();
    }

    return (
        <div className={"container-fluid bookings-container"}>
            <div className={"container-fluid d-flex flex-column"}>

                <div className="my-4">
                    <div className={"row"}>
                        <h5 className={"col-lg-6 my-auto"}>
                            Seasons
                        </h5>

                        <div className={"col-lg-6 d-flex justify-content-end"}>
                            <button
                                className={"button-primary mx-2 mx-lg-2 shadow"}
                                onClick={() => setShowModal(true)}
                            >
                                <p className={"m-2 text-white text-nowrap"}>Create New Season</p>
                            </button>
                        </div>
                    </div>

                    {seasons.map(season => {
                        return <Season key={season.name} onCancel={onCancel} {...season} />
                    })}

                </div>

            </div>

            <CreateSeasonModal show={showModal} handleClose={() => setShowModal(false)} handleSubmit={onCreation} />

        </div>
    );
}

export default Seasons;