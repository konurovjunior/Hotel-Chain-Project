import React, { useState, useEffect } from 'react';

import './Profile.css';

import { loadSeasonPrices } from '../../utils/api';

function Season({ name, minDate, maxDate, onCancel }) {
    const [prices, setPrices] = useState([]);
    const minDateOpt = (new Date(minDate));
    minDateOpt.setDate((new Date(minDate)).getDate() + 1);
    const maxDateOpt = (new Date(maxDate));
    maxDateOpt.setDate((new Date(maxDate)).getDate() + 1);
    const updatePrices = () => {
        loadSeasonPrices(name, (response, status) => {
            if (status === 200) {
                setPrices(response);
            }
            else {
                console.log(response);
            }
        });
    }

    useEffect(() => {
        updatePrices();
    }, []);

    return (
        <div className={"row py-3 px-5 my-3 shadow"}>
            <div className={"col-lg-4 d-flex flex-column justify-content-center"}>
                <div className="row my-2">

                    <h5 className="col-lg-12 p-0 my-auto d-flex justify-content-center">
                        {name}
                    </h5>

                </div>
            </div>

            <div className={"col-lg-3"}>
                <div className="row my-2">

                    <div className="col-lg-12">
                        <div className={"row my-auto"}>
                            <p className="col-4 col-lg-3 d-flex justify-content-end p-0">from:</p>
                            <p className="col-8 col-lg-9 p-0 px-2">{minDateOpt.toLocaleDateString()}</p>
                        </div>
                    </div>

                </div>
            </div>

            <div className={"col-lg-3"}>
                <div className="row my-2">

                    <div className="col-lg-12">
                        <div className={"row my-auto"}>
                            <p className="col-4 col-lg-3 d-flex justify-content-end p-0">to:</p>
                            <p className="col-8 col-lg-9 p-0 px-2">{maxDateOpt.toLocaleDateString()}</p>
                        </div>
                    </div>

                </div>
            </div>

            <div className={"col-lg-2 d-flex flex-column justify-content-center"}>
                <div className="row d-flex justify-content-end my-2 p-0">

                    {
                        onCancel
                            ?
                            <div className={"btn button-link p-0"} onClick={() => onCancel(name)}>
                                <p className="col-lg-12 text-danger">
                                    Delete
                                </p>
                            </div>
                            :
                            <div className={"btn disabled button-link p-0"}>
                                <p className="col-lg-12">
                                    Closed
                                </p>
                            </div>
                    }

                </div>
            </div>

            <div className={"col-12 my-1 py-2 separator-top"}>
                <div className={"row d-flex"}>

                    <div className={"col-12"}>
                        <div className={"row"}>
                            <div className={"col-2 mx-2 d-flex justify-content-end"}>
                                <label className={"my-1 mx-2"}><p className="m-0">Day:</p></label>
                            </div>
                            {["Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun"].map(day => {
                                return <div key={day} className={"col-1 mx-2 d-flex justify-content-center"}>
                                    <label className={"my-1 mx-0"}><p className="m-0">{day}</p></label>
                                </div>
                            })}
                        </div>
                    </div>

                    {prices.map(price => {
                        return <div key={price.roomTypeId} className={"col-12"}>
                            <div className={"row"}>
                                <div className={"col-2 mx-2 d-flex justify-content-end"}>
                                    <label className={"my-1 mx-1 d-flex flex-column justify-content-center"}>
                                        <p className="m-0">{price.roomTypeName}:</p>
                                    </label>
                                </div>

                                {["monday", "tuesday", "wednesday", "thursday", "friday", "saturday", "sunday"].map(day => {
                                    return <div key={day} className={"col-1 mx-2 p-0 d-flex justify-content-center"}>
                                        <p className={"my-1 mx-0 text-success"}>{price[day]}$</p>
                                    </div>
                                })}
                            </div>
                        </div>
                    })}

                </div>
            </div>

        </div>
    );
}

export default Season;