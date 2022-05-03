import React from 'react';
import { useMedia } from 'react-media';

import { Link } from 'react-router-dom';

import './Login.css';

import LoginForm from './LoginForm';

function LoginBlock({ handleClose }) {
    const isSmallScreen = useMedia({ query: "(max-width: 991px)" });

    return (
        <div className={"row mx-auto my-3 w-100"}>

            <div className={"col-lg-6"}>
                <div className={"text-center"}><h4>Please sign in</h4></div>
                <LoginForm />
            </div>

            <div className={"col-lg-6 d-flex flex-column justify-content-center" + (isSmallScreen ? "" : " separator-left")}>
                {
                    isSmallScreen
                        ?
                        <h4 className={"mx-auto logo mt-4"}>Hotel Chain</h4>
                        :
                        <h1 className={"mx-auto logo"}>Hotel Chain</h1>
                }

                <p className={"mx-auto"}>Don't have an account yet?</p>
                <button className={"button-primary mx-auto my-3 shadow"}><Link onClick={handleClose} to={"/register"}><p className="m-0 text-white">Join Now</p></Link></button>
            </div>

        </div>
    );
}

export default LoginBlock;