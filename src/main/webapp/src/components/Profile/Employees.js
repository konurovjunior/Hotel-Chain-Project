import React, { useState, useEffect } from 'react';

import './Profile.css';

import { loadEmployees } from '../../utils/api';
import Edit from './Edit';

function Employees() {
	const [employees, setEmployee] = useState([]);
	const [show, setShow] = useState("");
	const weekDays = ['Mon', 'Tue', 'Wed', 'Thu', 'Fri', 'Sat', 'Sun'];

	const updateEmployees = () => {
		loadEmployees((response, status) => {
			if (status === 200) {
				setEmployee(response);
			}
			else {
				console.log(response);
			}
		});
	}

	useEffect(() => {
		updateEmployees();
	}, []);

	const getWeekMappings = (s) => {
		let result = "";
		for (let i = 0; i < 7; ++i) {
			if (s.charAt(i) === "0") continue;
			if (result.length > 0) result += ", ";
			result += weekDays[i];
		}
		return result;
	}

	const Card = ({ username, type, workingDays, workingFrom, workingTill, hourlyRate }) => {
		return (
			<div className={"row py-3 px-5 my-3 shadow"}>
				<div className={"col-lg-3 d-flex flex-column justify-content-center"}>
					<div className="row my-2">

						<h5 className="col-lg-12 p-0 my-auto d-flex justify-content-center">
							{username}
						</h5>

						<p className="col-lg-12 p-0 d-flex justify-content-center">
							{type}
						</p>

					</div>
				</div>
				<div className={"col-lg-6"}>
					<div className="row my-2">
						<div className="col-lg-12">
							<div className={"row my-auto"}>
								<p className="col-12 p-0 px-2 d-flex justify-content-center">{getWeekMappings(workingDays)}</p>
							</div>
						</div>

						<div className="col-lg-12">
							<div className={"row my-auto d-flex justify-content-center"}>
								<p className="col-6 d-flex justify-content-end p-0">from:</p>
								<p className="col-6 p-0 px-2">{workingFrom}</p>
							</div>
						</div>

						<div className="col-lg-12">
							<div className={"row my-auto d-flex justify-content-center"}>
								<p className="col-6 d-flex justify-content-end p-0">to:</p>
								<p className="col-6 p-0 px-2">{workingTill}</p>
							</div>
						</div>

					</div>
				</div>
				<div className={"col-lg-2 d-flex flex-column justify-content-center"}>
					<div className="row my-2">

						<h5 className="col-lg-12 p-0 my-auto d-flex justify-content-center">
							{hourlyRate} USD
                        </h5>

					</div>
				</div>
				<div className={"col-lg-1 d-flex justify-content-end"}>
					<div className="row my-auto">
						<button onClick={() => { setShow(username); }} className={"justify-content-center button-primary shadow"}>
							<p className={"m-0 text-white"}>Edit</p>
						</button>
					</div>
				</div>

			</div>
		);
	}

	return (
		<div className={"container-fluid bookings-container"}>
			<div className={"container-fluid d-flex flex-column"}>

				<div className="my-12">
					<div className={"row"}><h5 className={"col-lg-12"}>Hotel Employees</h5></div>

					{employees.map((employee, id) => {
						return <div key={employee.username}>
							<Edit reload={updateEmployees} handleClose={() => setShow("")} show={employee.username === show} data={employee} />
							<Card {...employee} />
						</div>
					})}

				</div>
			</div>
		</div>
	);
}

export default Employees;