import React, { useState } from 'react';
import { useMedia } from 'react-media';

import { Link } from 'react-router-dom';

import './NavBar.css';

import { LoginModal } from '../Auth';
import AccountButton from './AccountButton';

function NavBar() {
    const [showMenu, setShowMenu] = useState(false);
    const [showLogin, setShowLogin] = useState(false);

    const isSmallScreen = useMedia({ query: "(max-width: 991px)" });

    const navigation_links = [
        ["Destinations", "/", 1],
        ["Discover", "/", 2],
        ["Activities", "/", 3],
        ["About", "/", 4],
    ]

    if (isSmallScreen) {
        return (
            <>
                <nav className={"navbar shadow"}>

                    <div className={"container-fluid row navbar-row m-0 px-0"}>

                        <div className={"col-2 d-flex justify-content-start"}>
                            <img
                                className={"navbar-menu-button"}
                                alt={"Menu"}
                                onClick={() => setShowMenu(showMenu => (showMenu ^ 1))}
                                src={"icons/navbar/menu.png"}
                            />
                        </div>

                        <div className={"col-8 d-flex justify-content-center"}>
                            <Link to={"/"} style={{ textDecoration: "none" }}><h4 className={"my-auto logo"}>Hotel Chain</h4></Link>
                        </div>

                        <div className={"col-2 d-flex justify-content-end"}>
                            <Link to={'/search'}>
                                <button className={"button-primary p-2 shadow"}>
                                    <p className={"my-0 text-white text-nowrap"}>BOOK</p>
                                </button>
                            </Link>
                        </div>

                    </div>

                    {
                        showMenu
                            ?
                            <div className={"container-fluid row navbar-row m-0 py-2"}>
                                {navigation_links.map(([text, path, key]) => {
                                    return (
                                        <div className={"col-12 d-flex justify-content-center"} key={key}>
                                            <Link to={path}><p className={"m-2 text-nowrap"}>{text}</p></Link>
                                        </div>
                                    );
                                })}

                                <AccountButton className={"col-12"} onLogin={() => setShowLogin(true)} />

                            </div>
                            :
                            ""
                    }

                    <LoginModal show={showLogin} handleClose={() => setShowLogin(false)} />

                </nav>
                <div className={"container-fluid navbar-placeholder"} />
            </>
        );
    }

    return (
        <>
            <nav className={"navbar shadow"}>

                <div className={"container-fluid row m-0"}>

                    <div className={"col-3 col-lg-2"}>
                        <Link to={"/"} style={{ textDecoration: "none" }}><h4 className={"my-auto logo"}>Hotel Chain</h4></Link>
                    </div>

                    <div className={"col-5 col-lg-6"}>
                        <div className={"w-100 d-flex justify-content-around"}>

                            {navigation_links.map(([text, path, key]) => {
                                return (
                                    <div className={"d-flex justify-content-center"} key={key}>
                                        <Link to={path}><p className={"m-2 text-nowrap"}>{text}</p></Link>
                                    </div>
                                );
                            })}

                        </div>
                    </div>

                    <div className={"col-3 col-lg-3 px-0"}>
                        <div className={"w-100 d-flex justify-content-end"}>

                            <Link to={'/search'}>
                                <button className={"button-primary mx-2 mx-lg-4 shadow"}>
                                    <p className={"m-2 text-white text-nowrap"}>Book Now</p>
                                </button>
                            </Link>

                            <AccountButton onLogin={() => setShowLogin(true)} />

                        </div>
                    </div>

                </div>

                <LoginModal show={showLogin} handleClose={() => setShowLogin(false)} />

            </nav>
            <div className={"container-fluid navbar-placeholder"} />
        </>
    );
}

export default NavBar;