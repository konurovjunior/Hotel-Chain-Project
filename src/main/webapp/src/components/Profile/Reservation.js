import React, { useState, useEffect } from 'react';

import './Profile.css';

import { loadReservationRooms, loadAvailableRooms, createAssignment, deleteAssignment } from '../../utils/api';

function Reservation({ guestEmail, roomTypeName, roomCnt, finalPrice, checkInDate, checkOutDate, reservationId, onCancel }) {
    const [rooms, setRooms] = useState([]);
    const [availableRooms, setAvailableRooms] = useState([]);
    const [dropdown, setDropdown] = useState(false);

    const updateRooms = () => {
        loadReservationRooms(reservationId, (response, status) => {
            if (status === 200) {
                setRooms(response);
            }
            else {
                console.log(response);
            }
        });
    }

    const updateAvailableRooms = () => {
        loadAvailableRooms(reservationId, (response, status) => {
            if (status === 200) {
                setAvailableRooms(response);
            }
            else {
                console.log(response);
            }
        });
    }

    useEffect(() => {
        updateRooms();
        updateAvailableRooms();
    }, []);

    const assignRoom = (roomNumber) => {
        let data = {
            reservationId: reservationId,
            roomNumber: roomNumber,
        }
        createAssignment(data, (response, status) => {
            if (status === 200) {
                updateAvailableRooms();
                updateRooms();
            }
            else {
                console.log(response);
            }
        });
    }

    const deassignRoom = (roomNumber) => {
        let data = {
            reservationId: reservationId,
            roomNumber: roomNumber,
        }
        deleteAssignment(data, (response, status) => {
            if (status === 200) {
                updateAvailableRooms();
                updateRooms();
            }
            else {
                console.log(response);
            }
        });
    }

    return (
        <div className={"row py-3 px-5 my-3 shadow"}>
            <div className={"col-lg-2 d-flex flex-column justify-content-center"}>
                <div className="row my-2">

                    <h5 className="col-lg-12 p-0 my-auto d-flex justify-content-center">
                        {guestEmail}
                    </h5>

                </div>
            </div>
            <div className={"col-lg-3"}>
                <div className="row my-2">

                    <p className="col-lg-12 p-0 d-flex justify-content-center">
                        {roomCnt} room{roomCnt > 1 ? "s" : ""}
                    </p>

                    <p className="col-lg-12 p-0 d-flex justify-content-center">
                        {roomTypeName}
                    </p>

                </div>
            </div>
            <div className={"col-lg-3"}>
                <div className="row my-2">

                    <div className="col-lg-12">
                        <div className={"row my-auto"}>
                            <p className="col-4 col-lg-3 d-flex justify-content-end p-0">from:</p>
                            <p className="col-8 col-lg-9 p-0 px-2">{checkInDate}</p>
                        </div>
                    </div>

                    <div className="col-lg-12">
                        <div className={"row my-auto"}>
                            <p className="col-4 col-lg-3 d-flex justify-content-end p-0">to:</p>
                            <p className="col-8 col-lg-9 p-0 px-2">{checkOutDate}</p>
                        </div>
                    </div>

                </div>
            </div>
            <div className={"col-lg-3 d-flex flex-column justify-content-center"}>
                <div className="row my-2">

                    <h5 className="col-lg-12 p-0 my-auto d-flex justify-content-center">
                        {finalPrice} USD
                    </h5>

                </div>
            </div>

            <div className={"col-lg-1 d-flex flex-column justify-content-center"}>
                <div className="row d-flex justify-content-center my-2 p-0">

                    {
                        onCancel
                            ?
                            <div className={"btn button-link p-0"} onClick={() => onCancel(reservationId)}>
                                <p className="col-lg-12 text-danger">
                                    Cancel
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

            <div className={"col-12 mt-3 pt-2 d-flex flex-column justify-content-center separator-top"}>
                <div className="row my-2 p-0">

                    <div className={"col-2 p-0 my-auto mr-3 d-flex justify-content-center"}>
                        <p>
                            Assigned rooms:
                        </p>
                    </div>

                    {rooms.map(room => {
                        return <div key={room.roomNumber} className={"p-0 mx-1 my-auto d-flex justify-content-center room"}>
                            <h5 className={"my-1 px-2 separator-right"}>{room.roomNumber}</h5>
                            <div
                                className={"btn button-link ml-2 mr-2 p-0 my-auto"}
                                onClick={() => deassignRoom(room.roomNumber)}
                            >
                                <p className="text-danger">
                                    x
                                </p>
                            </div>
                        </div>
                    })}

                    {
                        rooms.length === roomCnt
                            ?
                            <></>
                            :
                            <div
                                className={"btn button-link p-0 mx-1 my-auto assignment-dropdown"}
                                onClick={() => setDropdown(dropdown => (!dropdown))}
                                onBlur={() => setDropdown(false)}
                            >
                                <p className="col-lg-12 text-primary">
                                    Add
                                </p>
                                {
                                    dropdown
                                        ?
                                        <div className={"row assignment-dropdown-content"}>
                                            {availableRooms.map(room => {
                                                return <div
                                                    key={room.roomNumber}
                                                    className={"col-12 d-flex justify-content-center py-2 assignment-dropdown-item"}
                                                    onClick={() => assignRoom(room.roomNumber)}
                                                >
                                                    <p>{room.roomNumber}</p>
                                                </div>
                                            })}
                                        </div>
                                        :
                                        ""
                                }
                            </div>
                    }

                </div>
            </div>

        </div >
    );
}

export default Reservation;