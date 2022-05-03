import React, { useState } from 'react';

import { Link, useHistory } from 'react-router-dom';

import './NavBar.css';

import { getUsername, deleteUser } from '../../utils/auth';

function AccountButton({ onLogin, className }) {
    const [dropdown, setDropdown] = useState(false);
    const history = useHistory();

    const username = getUsername();
    const accountIcon = <img className={"navbar-account-icon my-auto mx-1"} alt={"Account"} src={"icons/navbar/account.png"} />;

    const logout = () => {
        deleteUser();
        history.push('/');
        window.location.reload();
    }

    if (username) return (
        <div
            className={"btn button-link d-flex justify-content-center account-dropdown " + className}
            onClick={() => setDropdown(dropdown => (!dropdown))}
            onBlur={() => setDropdown(false)}
            style={{ outline: "none" }}
        >
            {accountIcon}
            <div className={"my-auto mx-1"}>
                <p className={"m-2 text-nowrap"}>
                    {username}
                </p>
            </div>

            {
                dropdown
                    ?
                    <div className={"account-dropdown-content row"}>
                        <div className={"col-12 d-flex justify-content-center my-3"}>
                            <Link to={"/profile"}><p className={"m-0 text-nowrap"}>Profile</p></Link>
                        </div>
                        <div className={"col-12 d-flex justify-content-center my-3"}>
                            <div className={"button-link"} onClick={logout} ><p className={"m-0 text-nowrap text-danger"}>Logout</p></div>
                        </div>
                    </div>
                    :
                    ""
            }

        </div>
    );

    return (
        <div
            className={"btn button-link d-flex justify-content-center " + className}
            onClick={onLogin}
        >
            {accountIcon}
            <div className={"my-auto mx-1"}>
                <p className={"m-2 text-nowrap"}>
                    Sign In
                </p>
            </div>
        </div>
    );
}

export default AccountButton;