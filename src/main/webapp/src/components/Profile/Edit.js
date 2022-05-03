import React, { useState } from 'react';

import { updateEmployee } from '../../utils/api';
import { Modal } from '../Modal';

function Edit({ reload, handleClose, show, data, className }) {
	const [rate, setRate] = useState(data.hourlyRate);
	const [from, setFrom] = useState(data.workingFrom);
	const [till, setTill] = useState(data.workingTill);
	const [days, setDays] = useState(data.workingDays);
	const weekDays = ['Mon', 'Tue', 'Wed', 'Thu', 'Fri', 'Sat', 'Sun'];
	const replaceAt = (s, index) => {
		const replacement = "" + (s.charAt(index) ^ 1);
		let result = "";
		for (let i = 0; i < s.length; i++) {
			if (i === index) result += replacement;
			else result += s.charAt(i);
		}
		return result;
	}
	const onSubmit = () => {
		const reqData = {
			"workingDays": days,
			"from": from,
			"till": till,
			"hourlyRate": rate
		};
		updateEmployee(reqData, (response, status) => {
			if (status < 300) {
				handleClose();
				reload();
			} else {
				console.log(response);
				alert("Something went wrong, try again!");
			}
		}, `?username=${data.username}`);
		// makeBooking(reqData, (response, status) => {
		// 	if (status === 200 && response.success) {
		// 		console.log(response);
		// 		handleClose();
		// 	}
		// 	else {
		// 		console.log(response);
		// 		alert("Something is not correct, try again");
		// 	}
		// });
	}
	return (
		<Modal show={show} handleClose={handleClose} className={className}>
			<div className={"row mx-auto my-3 px-5"}>
				<div className={"col-lg-12 d-flex flex-column justify-content-center"}>
					<div className="row my-2">

						<h5 className="col-lg-12 p-0 d-flex justify-content-center">
							Adjust working times:
						</h5>

					</div>
				</div>
				<div className={"col-lg-12"}>
					<div className="row my-2 d-flex justify-content-center">
						{weekDays.map((day, id) => {
							return <div className={"col-1 mx-0 d-flex justify-content-center"} key={id}>
								<div className={"d-flex flex-column justify-content-center mr-2"}>
									<input type="checkbox" checked={days.charAt(id) === "1"}
										onChange={() => setDays(replaceAt(days, id))} />
								</div>
								<p>
									{day}
								</p>
							</div>
						})}

					</div>
				</div>
				<div className={"col-lg-6 d-flex flex-column justify-content-center"}>
					<div className="row my-2">
						<div className="col-lg-12 my-1">
							<div className={"row"}>
								<p className="col-6 d-flex justify-content-end">from:</p>
								<input type="time" className="col-6 p-0 px-2"
									value={from}
									onChange={e => setFrom(e.target.value)} />
							</div>
						</div>

						<div className="col-lg-12 my-1">
							<div className={"row"}>
								<p className="col-6 d-flex justify-content-end">till:</p>
								<input type="time" className="col-6 p-0 px-2"
									value={till}
									onChange={e => setTill(e.target.value)} />
							</div>
						</div>

					</div>
				</div>

				<div className={"col-6 d-flex flex-column justify-content-center"}>
					<div className="row my-2">

						<div className="col-lg-12 d-flex flex-column justify-content-start">
							<div className={"row d-flex justify-content-start mx-3"}>
								<p className="d-flex flex-column justify-content-center mx-2">Hourly payment:</p>
								<div className="">
									<input type="number" value={rate} min={"0"} onChange={e => setRate(e.target.value)} />
								</div>
							</div>
						</div>

					</div>
				</div>
				<div className={"col-12"}>
					<div className="row mt-4 d-flex justify-content-center">
						<button onClick={onSubmit} className={"justify-content-center button-primary shadow"}><p className={"m-0 text-white"}>Confirm</p></button>
					</div>
				</div>
			</div>
		</Modal>
	);
}

export default Edit;