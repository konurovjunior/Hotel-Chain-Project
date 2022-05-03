import React, { useState, useEffect } from 'react';

import { useMedia } from 'react-media';

import { loadAnnouncements } from '../../utils/api';

function TitleSection() {
    const isSmallScreen = useMedia({ query: "(max-width: 991px)" });

    const [announcements, setAnnouncements] = useState([]);
    const [index, setIndex] = useState(0);

    const updateAnnouncements = () => {
        loadAnnouncements((response, status) => {
            if (status === 200) {
                setAnnouncements(response.reverse());
            }
            else {
                console.log(response);
            }
        });
    }

    useEffect(() => {
        updateAnnouncements();
    }, []);

    const header = "Ultra Lorem Ipsum";
    const body = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Curabitur eleifend laoreet sem. Duis id lacinia elit. Lorem ipsum dolor sit amet, consectetur adipiscing elit.";
    const imageUrl = "https://images.pexels.com/photos/60217/pexels-photo-60217.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=650&w=940";

    if (isSmallScreen) {
        return (
            <div>
                <div className={"container-fluid home-page-section d-flex flex-column justify-content-end px-4 py-3"} style={{
                    backgroundImage: "url(" + imageUrl + ")",
                }}>
                    <h1 className={"mx-auto text-white"}>{header}</h1>
                </div>
                <div className={"container-fluid home-page-section-fluid py-2 px-4 shadow"}>
                    <p className={"my-4"}>
                        {body}
                    </p>
                </div>
            </div>
        );
    }

    return (
        <div className={"container-fluid home-page-section shadow"} style={{
            backgroundImage: "url(" + imageUrl + ")",
        }}>
            <div className={"row mx-2 h-100"}>
                <div className={"col-lg-6 d-flex justify-content-center"}>
                    <div className={"row my-auto mx-5"}>
                        <h1 className={"col-sm-12 text-white"}>{header}</h1>
                        <p className={"col-sm-12 text-white"}>
                            {body}
                        </p>
                    </div>
                </div>

                {
                    announcements.length > 0
                        ?
                        <div className={"col-lg-6 d-flex justify-content-center"}>
                            <div className={"row mx-5 my-auto d-flex justify-content-center announcement-container"}>

                                {
                                    index > 0
                                        ?
                                        <div
                                            className={"col-1 btn button-link d-flex justify-content-center arrow-container"}
                                            onClick={() => setIndex(index => index - 1)}
                                        >
                                            <img className={"arrow-icon my-auto"} alt={"Previous"} src={"icons/arrow-left.png"} />
                                        </div>
                                        :
                                        <div className={"col-1 d-flex justify-content-center"}>
                                        </div>
                                }

                                <div className={"col-10 d-flex justify-content-center h-100 w-100"}>
                                    <div className={"row my-auto p-3 announcement-content-container"}>
                                        <div className={"col-sm-12"}>
                                            <h5><b>{announcements[index].title}</b></h5>
                                        </div>
                                        <div className={"col-sm-12"}>
                                            <div className={"row"}>
                                                <p className={"col-6"}><b>{announcements[index].hotel}</b></p>
                                                <p className={"col-6 d-flex justify-content-end"}><b>{(new Date(announcements[index].date)).toLocaleDateString()}</b></p>
                                            </div>
                                        </div>
                                        <div className={"col-sm-12"}>
                                            <p>
                                                {announcements[index].text}
                                            </p>
                                        </div>
                                    </div>
                                </div>

                                {
                                    index < announcements.length - 1
                                        ?
                                        <div
                                            className={"col-1 btn button-link d-flex justify-content-center arrow-container"}
                                            onClick={() => setIndex(index => index + 1)}
                                        >
                                            <img className={"arrow-icon my-auto"} alt={"Next"} src={"icons/arrow-right.png"} />
                                        </div>
                                        :
                                        <div className={"col-1 d-flex justify-content-center"}>
                                        </div>
                                }

                            </div>
                        </div>
                        :
                        ""
                }
            </div>
        </div>
    );
}

export default TitleSection;