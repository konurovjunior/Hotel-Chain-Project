import React from 'react';

import './Modal.css';

// import { useMedia } from 'react-media';

function Modal({ show, handleClose, className, children }) {
    // const isSmallScreen = useMedia({ query: "(max-width: 991px)" });

    if (!show) return <></>;

    return (
        <div className={"modal-background"} onClick={handleClose}>
            <div
                className={"modal-main shadow " + className}
                onClick={e => e.stopPropagation()}
            >
                {children}
            </div>
        </div >
    );
}

export default Modal;