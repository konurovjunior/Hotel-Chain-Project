import React, { useState, useEffect } from 'react';

import { Modal } from '../Modal';

import { loadRoomTypes, createSeason } from '../../utils/api';

function CreateSeasonModal({ show, handleClose, className, handleSubmit }) {
    const [roomTypes, setRoomTypes] = useState([]);
    const [data, setData] = useState({});

    const updateRoomTypes = () => {
        loadRoomTypes((response, status) => {
            if (status === 200) {
                setRoomTypes(response);
            }
            else {
                console.log(response);
            }
        });
    }

    useEffect(() => {
        updateRoomTypes();
    }, []);

    const onChange = (event) => {
        let name = event.target.name;
        let value = event.target.value;
        setData(data => ({ ...data, [name]: value }));
    };

    const converted = (s) => {
        return s.substring(5, 7) + "/" + s.substring(8) + "/" + s.substring(0, 4);
    }

    const onSubmit = (event) => {
        event.preventDefault();

        let roomTypes = [];
        let otherData = {};
        const days = ["monday", "tuesday", "wednesday", "thursday", "friday", "saturday", "sunday"];
        for (var field in data) {
            if (field[0] === '-') {
                let found = false;
                for (var roomType of roomTypes) {
                    if (roomType.roomTypeId === field.substring(2)) {
                        roomType[days[parseInt(field[1], 10)]] = data[field];
                        found = true;
                        break;
                    }
                }
                if (!found) {
                    roomTypes.push({
                        roomTypeId: field.substring(2),
                        [days[parseInt(field[1], 10)]]: data[field],
                    });
                }
            }
            else {
                otherData[field] = data[field];
            }
        }

        if (otherData.startDate && otherData.startDate.length === 10) {
            otherData.startDate = converted(otherData.startDate);
        }
        if (otherData.endDate && otherData.endDate.length === 10) {
            otherData.endDate = converted(otherData.endDate);
        }

        createSeason(roomTypes, otherData, (response, status) => {
            if (status === 200) {
                handleSubmit();
            }
            else {
                console.log(response);
            }
        });
    }

    return (
        <Modal show={show} handleClose={handleClose} className={className}>
            <div className={"row mx-auto my-3 w-100 bookings-container"}>

                <form
                    className={"mx-auto"}
                    onChange={onChange}
                    onSubmit={onSubmit}
                    style={{ width: "70%" }}
                >
                    <div className={"row mx-2"}>
                        <h4 className={"col-12 d-flex justify-content-center"}>Create Season</h4>

                        <div className={"col-12 form-input-section"}>
                            <label className={"my-1 mx-1"}><p className="m-0">Season Name</p></label>
                            <input name={"newSeasonName"} className={"my-1 mx-1"} type={"text"}></input>
                        </div>

                        <div className={"col-12 form-input-section mt-3"}>
                            <div className={"row"}>
                                <div className={"col-6 form-input-section"}>
                                    <label className={"mx-1"}><p className="m-0">Start Date</p></label>
                                    <input name={"startDate"} className={"my-0 mx-1"} type={"date"}></input>
                                </div>

                                <div className={"col-6 form-input-section"}>
                                    <label className={"mx-1"}><p className="m-0">End Date</p></label>
                                    <input name={"endDate"} className={"my-0 mx-1"} type={"date"}></input>
                                </div>
                            </div>
                        </div>

                        <div className={"col-12 separator-top mt-5 py-4"}>
                            <div className={"row"}>
                                <h5 className={"col-12 mb-3 d-flex justify-content-center"}>Prices</h5>

                                <div className={"col-12"}>
                                    <div className={"row"}>
                                        <div className={"col-3 d-flex justify-content-end"}>
                                            <label className={"my-1 mx-1"}><p className="m-0">Day:</p></label>
                                        </div>
                                        {["M", "T", "W", "R", "F", "Sat", "Sun"].map(day => {
                                            return <div key={day} className={"col-1 d-flex justify-content-center"}>
                                                <label className={"my-1 mx-0"}><p className="m-0">{day}</p></label>
                                            </div>
                                        })}
                                    </div>
                                </div>

                                {roomTypes.map(roomType => {
                                    return <div key={roomType.roomTypeId} className={"col-12"}>
                                        <div className={"row"}>
                                            <div className={"col-3 d-flex justify-content-end"}>
                                                <label className={"my-1 mx-1 d-flex flex-column justify-content-center"}>
                                                    <p className="m-0">{roomType.roomTypeName}:</p>
                                                </label>
                                            </div>

                                            {[0, 1, 2, 3, 4, 5, 6].map(day => {
                                                return <div key={day} className={"col-1 p-1"}>
                                                    <input name={"-" + day + roomType.roomTypeId} className={"my-1 mx-0 w-100"} type={"number"}></input>
                                                </div>
                                            })}
                                        </div>
                                    </div>
                                })}
                            </div>
                        </div>

                        <div className={"col-12 separator-top my-4 py-4"}>
                            <div className={"row"}>
                                <h5 className={"col-12 d-flex justify-content-center"}>Announcement</h5>
                                <div className={"col-12 form-input-section"}>
                                    <label className={"my-1 mx-1"}><p className="m-0">Title</p></label>
                                    <input name={"title"} className={"my-1 mx-1"} type={"text"}></input>
                                </div>

                                <div className={"col-12 form-input-section"}>
                                    <label className={"mx-1"}><p className="m-0">Text</p></label>
                                    <input name={"text"} className={"mx-1"} type={"text"}></input>
                                </div>
                            </div>
                        </div>

                        <div className={"col-12 form-input-section my-3"}>
                            <button className={"button-primary mx-1 shadow"}><p className={"m-0 text-white"}>Create</p></button>
                        </div>

                    </div>
                </form >

            </div>
        </Modal>
    );
}

export default CreateSeasonModal;