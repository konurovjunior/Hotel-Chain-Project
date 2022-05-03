import React, { useState, useEffect } from 'react';

import './Profile.css';

import Reservation from './Reservation';

import { loadReservations, cancelBooking } from '../../utils/api';

function Reservations() {
    const [reservations, setReservations] = useState([]);

    const updateReservations = () => {
        loadReservations((response, status) => {
            if (status === 200) {
                setReservations(response);
            }
            else {
                console.log(response);
            }
        });
    }

    useEffect(() => {
        updateReservations();
    }, []);

    const onCancel = (id) => {
        cancelBooking(id, (response, status) => {
            if (status === 200) {
                updateReservations();
            }
            else {
                console.log(response);
            }
        });
    }

    return (
        <div className={"container-fluid bookings-container"}>
            <div className={"container-fluid d-flex flex-column"}>

                <div className="my-4">
                    <div className={"row"}><h5 className={"col-lg-12"}>Reservations</h5></div>

                    {reservations.map(reservation => {
                        return <Reservation key={reservation.reservationId} onCancel={onCancel} {...reservation} />
                    })}

                </div>

            </div>
        </div>
    );
}

export default Reservations;