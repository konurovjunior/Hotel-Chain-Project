import React from 'react';

import { Modal } from '../../Modal';
import LoginBlock from './LoginBlock';

function LoginModal({ show, handleClose, className }) {
    return (
        <Modal show={show} handleClose={handleClose} className={className}>
            <LoginBlock handleClose={handleClose} />
        </Modal>
    );
}

export default LoginModal;