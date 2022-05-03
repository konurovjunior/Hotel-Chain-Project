import React, { useState } from 'react';
import { useMedia } from 'react-media';
import { Redirect } from 'react-router-dom';

import './Profile.css';

import GeneralInfo from './GeneralInfo';
import Bookings from './Bookings';
import Reservations from './Reservations';
import Employees from './Employees';
import Seasons from './Seasons';

import { getUsername, getRole } from '../../utils/auth';

function ProfilePage() {
    const isSmallScreen = useMedia({ query: "(max-width: 991px)" });
    let tabs = [
        {
            key: 0,
            name: "General Info",
            content: <GeneralInfo />,
        },
        {
            key: 1,
            name: "Bookings",
            content: <Bookings />,
        },
    ]

    if (getRole() === 'clerk') {
        tabs = [
            {
                key: 0,
                name: "General Info",
                content: <GeneralInfo />,
            },
            {
                key: 1,
                name: "Reservations",
                content: <Reservations />,
            },
        ];
    }

    if (getRole() === 'manager') {
        tabs = [
            {
                key: 0,
                name: "General Info",
                content: <GeneralInfo />,
            },
            {
                key: 1,
                name: "Reservations",
                content: <Reservations />,
            },
            {
                key: 2,
                name: "Employees",
                content: <Employees />,
            },
            {
                key: 3,
                name: "Seasons",
                content: <Seasons />,
            },
        ];
    }

    const [currentTab, setCurrentTab] = useState(0);

    if (!getUsername()) {
        return <Redirect to={"/"} />
    }

    return (
        <div className={"container-fluid d-flex justify-content-center mt-4"}>
            <div className={"row profile-container mt-3"}>

                <div className={"col-lg-3" + (isSmallScreen ? "" : " separator-right")}>
                    <div className={"row py-3"}>
                        {tabs.map((tab) => {
                            return <div key={tab.key} className={"col-12 d-flex justify-content-center my-3"}>
                                <div
                                    className={"btn button-link p-0"}
                                    onClick={() => setCurrentTab(tab.key)}
                                >
                                    <h5 className={"m-0"}>
                                        {
                                            tab.key === currentTab
                                                ?
                                                <u>{tab.name}</u>
                                                :
                                                tab.name
                                        }
                                    </h5>
                                </div>
                            </div>
                        })}
                    </div>
                </div>

                <div className={"col-lg-9"}>
                    {tabs[currentTab].content}
                </div>

            </div>
        </div>
    );
}

export default ProfilePage;