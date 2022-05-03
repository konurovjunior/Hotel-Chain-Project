import React, { useState } from 'react';
import BookModal from './BookModal';

function Room(data) {
	const [show, setShow] = useState(false);
	const room = data.room;
	const logged = (localStorage.getItem("username", null) !== null && localStorage.getItem("username", null) !== '');

	const handleClose = (reload = false) => {
		setShow(false);
		if (reload) window.location.reload();
	}

	return (
		<div className={"row py-3 my-3 shadow"}>
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

					<p className="col-lg-12 p-0 d-flex justify-content-center">
						{room.availableRoomCnt} room{room.availableRoomCnt !== 1 ? "s" : ""} available
                        </p>

					<p className="col-lg-12 p-0 d-flex justify-content-center">
						{room.roomTypeName}
					</p>

				</div>
			</div>
			<div className={"col-lg-2 d-flex flex-column justify-content-center"}>
				<div className="row my-2">

					<div className="col-lg-12">
						<div className={"row"}>
							<p className="col-6 col-lg-3 d-flex justify-content-end p-0">capacity:</p>
							<p className="col-6 col-lg-9 p-0 px-2">{room.capacity}</p>
						</div>
					</div>

				</div>
			</div>
			<div className={"col-lg-3 d-flex flex-column justify-content-center"}>
				<div className="row my-2">

					<h5 className="col-lg-12 p-0 d-flex justify-content-center">
						{room.price} USD
                        </h5>

				</div>
			</div>
			{logged && <div className={"col-lg-1 d-flex flex-column justify-content-center"}>
				<BookModal handleClose={handleClose} show={show} data={room} />
				<div className="row my-2">
					<button onClick={() => setShow(true)} className={"justify-content-center button-primary shadow"}><p className={"m-0 text-white"}>Open</p></button>
				</div>
			</div>}
		</div>
	);
}

export default Room;