import React, { useState, useRef } from 'react';
import { useHistory } from 'react-router-dom';

import { registerUser } from '../../../utils/api';

function RegistrationForm() {
    const [data, setData] = useState({
        firstName: "",
        lastName: "",
        username: "",
        password: "",
        mobilePhone: "",
        address: "",
        identificationType: "",
        identificationNumber: "",
    });
    const verifyPassword = useRef(null);
    const [errorMessage, setErrorMessage] = useState("");

    const history = useHistory();

    const onChange = (event) => {
        let name = event.target.name;
        let value = event.target.value;
        setData(data => ({ ...data, [name]: value }));
    };

    const onSubmit = (event) => {
        event.preventDefault();

        if (verifyPassword.current.value !== data.password) {
            setErrorMessage("Passwords does not match");
            return;
        }

        registerUser(data, (response, status) => {
            if (status === 200) {
                history.push('/');
                window.location.reload();
            }
            else if (status === 400) {
                let firstMessageEnd = response.message.indexOf('\n');
                if (firstMessageEnd === -1) firstMessageEnd = response.message.length;

                setErrorMessage(response.message.substring(0, firstMessageEnd));
            }
            else {
                console.log(response);
            }
        });
    }

    return (
        <form onChange={onChange} onSubmit={onSubmit}>
            <div className={"row mx-2"}>

                <div className={"col-lg-6 form-input-section"}>
                    <label className={"my-1 mx-1"}><p className="m-0">First Name</p></label>
                    <input name={"firstName"} className={"my-1 mx-1"} type={"text"}></input>
                </div>

                <div className={"col-lg-6 form-input-section"}>
                    <label className={"my-1 mx-1"}><p className="m-0">Last Name</p></label>
                    <input name={"lastName"} className={"my-1 mx-1"} type={"text"}></input>
                </div>

                <div className={"col-12 form-input-section"}>
                    <label className={"my-1 mx-1"}><p className="m-0">Username</p></label>
                    <input name={"username"} className={"my-1 mx-1"} type={"text"}></input>
                </div>

                <div className={"col-lg-6 form-input-section"}>
                    <label className={"mx-1"}><p className="m-0">Password</p></label>
                    <input name={"password"} className={"mx-1"} type={"password"}></input>
                </div>

                <div className={"col-lg-6 form-input-section"}>
                    <label className={"mx-1"}><p className="m-0">Verify Password</p></label>
                    <input ref={verifyPassword} className={"mx-1"} type={"password"}></input>
                </div>

                <div className={"col-12 form-input-section"}>
                    <label className={"my-1 mx-1"}><p className="m-0">Phone number</p></label>
                    <input name={"mobilePhone"} className={"my-1 mx-1"} type={"tel"}></input>
                </div>

                <div className={"col-12 form-input-section"}>
                    <label className={"my-1 mx-1"}><p className="m-0">Address</p></label>
                    <input name={"address"} className={"my-1 mx-1"} type={"text"}></input>
                </div>

                <div className={"col-lg-6 form-input-section"}>
                    <label className={"my-1 mx-1"}><p className="m-0">Identification type</p></label>
                    <input name={"identificationType"} className={"my-1 mx-1"} type={"text"}></input>
                </div>

                <div className={"col-lg-6 form-input-section"}>
                    <label className={"my-1 mx-1"}><p className="m-0">Identification number</p></label>
                    <input name={"identificationNumber"} className={"my-1 mx-1"} type={"text"}></input>
                </div>

                <div className={"col-12 form-input-section"}>
                    <p className="my-0 mx-1 text-danger">{errorMessage}</p>
                </div>

                <div className={"col-12 form-input-section my-3"}>
                    <button className={"button-primary mx-1 shadow"}><p className={"m-0 text-white"}>Sign Up</p></button>
                </div>

            </div>
        </form >
    );
}

export default RegistrationForm;