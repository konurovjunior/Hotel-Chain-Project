import React from 'react';
import Room from './Room';

function Results({ results }) {

    return (
        <div className={"container-fluid"}>
            <div className={"container-fluid d-flex flex-column"}>

                <div className="my-4">

                    {results.map((room, id) => {
                        return <Room key={id} room={room} />
                    })}

                </div>

            </div>
        </div>
    );
}

export default Results;