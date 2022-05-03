import React, { useState, useEffect } from 'react';

import { searchRooms, loadHotels, loadHotelRoomTypes } from '../../utils/api';

function SearchForm({ onResultsLoad }) {
    const [data, setData] = useState({
        checkInDate: "",
        checkOutDate: "",
    });

    const [hotels, setHotels] = useState([]);
    const [roomTypes, setRoomTypes] = useState([]);
    const [hotelId, setHotelId] = useState("");

    const updateHotels = () => {
        loadHotels((response, status) => {
            if (status === 200) {
                setHotels(response);
            }
            else {
                console.log(response);
            }
        });
    }

    const updateRoomTypes = () => {
        if (hotelId) {
            loadHotelRoomTypes(hotelId, (response, status) => {
                if (status === 200) {
                    setRoomTypes(response);
                }
                else {
                    console.log(response);
                }
            });
        }
        else {
            setRoomTypes([]);
        }
    }

    useEffect(() => {
        updateHotels();
    }, []);

    useEffect(() => {
        setData(data => ({ ...data, roomTypeId: "" }));
        updateRoomTypes();
    }, [hotelId]);

    const onChange = (event) => {
        let name = event.target.name;
        let value = event.target.value;
        setData(data => ({ ...data, [name]: value }));
    };

    const onSubmit = (event) => {
        event.preventDefault();
        localStorage.setItem('checkInDate', data.checkInDate);
        localStorage.setItem('checkOutDate', data.checkOutDate);
        searchRooms(data, (response, status) => {
            if (status === 200) {
                onResultsLoad(response);
            }
            else {
                console.log(response);
                onResultsLoad([]);
            }
        });
    }

    return (
        <div className={"d-flex justify-content-center mx-auto"}>
            <form onChange={onChange} onSubmit={onSubmit}>
                <div className={"row mx-2"}>

                    <div className={"col-6 form-input-section"}>
                        <label className={"mx-1"}><h5 className="m-0">Hotels</h5></label>
                        <div>
                            <input
                                onChange={e => { if (e.target.checked) setHotelId("") }}
                                name={"hotelId"}
                                className={"mx-1"}
                                type={"radio"}
                                value={""}
                                checked={!(data.hotelId)}
                            />
                            <label className={"mx-1"}>
                                <p className="m-0">Any</p>
                            </label>
                        </div>
                        {hotels.map(hotel => {
                            return <div key={hotel.id}>
                                <input
                                    onChange={e => { if (e.target.checked) setHotelId(hotel.id) }}
                                    name={"hotelId"}
                                    className={"mx-1"}
                                    type={"radio"}
                                    value={hotel.id}
                                />
                                <label className={"mx-1"}>
                                    <p className="m-0">{hotel.name}</p>
                                </label>
                            </div>
                        })}
                    </div>

                    <div className={"col-6 form-input-section d-flex justify-content-start"}>
                        <label className={"mx-1"}><h5 className="m-0">Room Types</h5></label>
                        <div>
                            <input
                                onChange={() => { }}
                                name={"roomTypeId"}
                                className={"mx-1"}
                                type={"radio"}
                                value={""}
                                checked={!(data.roomTypeId)}
                            />
                            <label className={"mx-1"}><p className="m-0">Any</p></label>
                        </div>
                        {roomTypes.map(roomType => {
                            return <div key={roomType.roomTypeId}>
                                <input name={"roomTypeId"} className={"mx-1"} type={"radio"} value={roomType.roomTypeId} />
                                <label className={"mx-1"}><p className="m-0">{roomType.name}</p></label>
                            </div>
                        })}
                    </div>

                    <div className={"col-12"}>
                        <div className="row">
                            <div className={"col-6 form-input-section"}>
                                <label className={"mx-1"}><p className="m-0">from</p></label>
                                <input name={"checkInDate"} className={"mx-1"} type={"date"}></input>
                            </div>
                            <div className={"col-6 form-input-section"}>
                                <label className={"my-1 mx-1"}><p className="m-0">to</p></label>
                                <input name={"checkOutDate"} className={"my-1 mx-1"} type={"date"}></input>
                            </div>
                        </div>
                    </div>

                    <div className={"col-12 form-input-section my-3"}>
                        <button className={"button-primary mx-1 shadow"}><p className={"m-0 text-white"}>Search</p></button>
                    </div>

                </div>
            </form >
        </div>
    );
}

export default SearchForm;