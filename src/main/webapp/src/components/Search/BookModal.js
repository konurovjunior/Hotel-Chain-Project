import React, { useState } from 'react';

import { makeBooking } from '../../utils/api';
import { Modal } from '../Modal';

function isNumeric(str) {
	if (typeof str != "string") return false // we only process strings!  
	return !isNaN(str) && // use type coercion to parse the _entirety_ of the string (`parseFloat` alone does not do this)...
		!isNaN(parseFloat(str)) // ...and ensure strings of whitespace fail
}

function BookModal({ handleClose, show, data, className }) {
	const [roomCount, setCount] = useState("0");
	const room = data;
	const logged = (localStorage.getItem("role", null) !== null && localStorage.getItem("role", null) === '');
	const onBook = () => {
		const reqData = {
			'hotelId': room.hotelId,
			'guest': localStorage.getItem('username'),
			'roomTypeId': room.roomTypeId,
			'roomCount': roomCount,
			'checkIn': localStorage.getItem('checkInDate'),
			'checkOut': localStorage.getItem('checkOutDate')
		}
		makeBooking(reqData, (response, status) => {
			if (status === 200 && response.success) {
				console.log(response);
				handleClose(true);
			}
			else {
				console.log(response);
				alert("Something is not correct, try again");
			}
		});
	}
	return (
		<Modal show={show} handleClose={handleClose} className={className}>
			<div className={"row py-3 my-3 mx-5"}>
				<div className={"col-lg-3"}>
					<div className="row my-2">

						<h5 className="col-lg-12 p-0 d-flex justify-content-center">
							{room.hotelName}
						</h5>

						<p className="col-lg-12 p-0 d-flex justify-content-center">
							{room.hotelAddress}
						</p>

					</div>
				</div>
				<div className={"col-lg-3"}>
					<div className="row my-2">

						<div className="col-lg-12 p-0 d-flex justify-content-center">
							<input
								className={"mx-1"}
								type="number"
								id="quantity"
								name="quantity"
								min="0"
								max={"" + room.availableRoomCnt}
								value={roomCount}
								onChange={(e) => {
									if (isNumeric(e.target.value))
										setCount(e.target.value)
								}
								} /> <h5>/</h5>
							<h5 className={"mx-1"}>
								{room.availableRoomCnt} room{room.availableRoomCnt !== 1 ? "s" : ""}
							</h5>
						</div>

						<p className="col-lg-12 p-0 d-flex justify-content-center">
							{room.roomTypeName}
						</p>

					</div>
				</div>
				<div className={"col-2 d-flex flex-column justify-content-center"}>
					<div className="row">
						<div className="col-6 d-flex justify-content-end px-1"><p>capacity:</p></div>
						<div className="col-6 px-1"><p>{room.capacity}</p></div>
					</div>
				</div>
				<div className={"col-lg-3 d-flex flex-column justify-content-center"}>
					<div className="row my-2">

						<h5 className="col-lg-12 p-0 d-flex justify-content-center">
							{parseInt(roomCount) * room.price} USD
						</h5>

					</div>
				</div>
				{logged && <div className={"col-lg-1 d-flex flex-column justify-content-center"}>
					<div className="row my-2">
						<button onClick={onBook} className={"justify-content-center button-primary shadow"}><p className={"m-0 text-white"}>Book</p></button>
					</div>
				</div>}
			</div>
		</Modal>
	);
}

export default BookModal;