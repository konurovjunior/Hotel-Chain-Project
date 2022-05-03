import React, { useState } from 'react';
import { useHistory } from 'react-router-dom';

import { saveUser } from '../../../utils/auth';
import { loginUser } from '../../../utils/api';

function LoginForm() {
    const [data, setData] = useState({
        username: "",
        password: "",
    });
    const [errorMessage, setErrorMessage] = useState("");

    const history = useHistory();

    const onChange = (event) => {
        let name = event.target.name;
        let value = event.target.value;
        setData(data => ({ ...data, [name]: value }));
    };

    const onSubmit = (event) => {
        event.preventDefault();

        loginUser(data, (response, status) => {
            if (status === 200) {
                saveUser(response);
                history.push('/');
                window.location.reload();
            }
            else if (status === 401) {
                setErrorMessage("Bad Credentials");
            }
            else {
                console.log(response);
            }
        });
    }

    return (
        <form onChange={onChange} onSubmit={onSubmit}>
            <div className={"row mx-2"}>

                <div className={"col-12 form-input-section"}>
                    <label className={"my-1 mx-1"}><p className="m-0">Username</p></label>
                    <input name={"username"} className={"my-1 mx-1"} type={"text"}></input>
                </div>

                <div className={"col-12 form-input-section"}>
                    <label className={"mx-1"}><p className="m-0">Password</p></label>
                    <input name={"password"} className={"mx-1"} type={"password"}></input>
                </div>

                <div className={"col-12 form-input-section"}>
                    <p className="my-0 mx-1 text-danger">{errorMessage}</p>
                </div>

                <div className={"col-12 form-input-section my-3"}>
                    <button className={"button-primary mx-1 shadow"}><p className={"m-0 text-white"}>Sign In</p></button>
                </div>

            </div>
        </form >
    );
}

export default LoginForm;