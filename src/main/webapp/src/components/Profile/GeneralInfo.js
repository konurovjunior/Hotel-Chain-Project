import React, { useState, useEffect } from 'react';

import './Profile.css';

import { loadUserInfo } from '../../utils/api';

function GeneralInfo() {
    const [userInfo, setUserInfo] = useState({});

    useEffect(() => {
        loadUserInfo((response, status) => {
            if (status === 200) {
                setUserInfo(response);
            }
            else {
                console.log(response);
            }
        });
    }, []);

    const Field = ({ name, value }) => {
        return (
            <div className={"row mb-4"}>

                <div className={"col-6 d-flex justify-content-end"}>
                    <p className={"text-nowrap"}>{name}:</p>
                </div>
                <div className={"col-6"}>
                    <p>{value}</p>
                </div>

            </div>
        );
    }

    return (
        <div className={"container-fluid"}>
            <div className={"row my-4"}>
                <div className={"col-12 d-flex justify-content-center"}><h4>{userInfo.username}</h4></div>
            </div>

            <Field name={"First Name"} value={userInfo.firstName} />
            <Field name={"Last Name"} value={userInfo.lastName} />
            <Field name={"Mobile Phone"} value={userInfo.mobilePhone} />
            <Field name={"Address"} value={userInfo.address} />
            <Field name={"Identification Type"} value={userInfo.identificationType} />
            <Field name={"Identification Number"} value={userInfo.identificationNumber} />
        </div>
    );
}

export default GeneralInfo;