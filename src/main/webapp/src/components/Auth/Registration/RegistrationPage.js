import React from 'react';

import RegistrationForm from './RegistrationForm';

function RegistrationPage() {
    return (
        <div className={"row mx-auto my-5 w-100"}>

            <div className={"col-lg-6 mx-auto"}>
                <div className={"text-center"}><h4>Please sign up</h4></div>
                <RegistrationForm />
            </div>

        </div>
    );
}

export default RegistrationPage;