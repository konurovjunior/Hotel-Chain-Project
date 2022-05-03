import React from 'react';

import { useMedia } from 'react-media';

function Feature(props) {
    const isSmallScreen = useMedia({ query: "(max-width: 991px)" });

    const justifyContentSide = (props.flip ? "justify-content-end" : "justify-content-start");

    const image = (
        <div className={"col-12 col-lg-6 d-flex " + justifyContentSide + " my-auto px-0"}>
            <img className={"home-page-feature-image"} alt={"Feature"} src={props.feature.imageSrc} />
        </div>
    );

    const text = (
        <div className="col-12 col-lg-6 my-auto">
            <div className={"row" + (isSmallScreen ? " mx-1 my-4" : " m-4")}>
                <h1 className={"col-sm-12"}>{props.feature.header}</h1>
                <p className={"col-sm-12"}>
                    {props.feature.body}
                </p>
            </div>
        </div>
    );

    return (
        <div className={"home-page-feature-container shadow"}>
            <div className={"row w-100 m-0 p-0"}>

                {props.flip && !isSmallScreen
                    ?
                    <>
                        {text}
                        {image}
                    </>
                    :
                    <>
                        {image}
                        {text}
                    </>
                }

            </div>
        </div>
    );
}

function FeaturesSection() {
    const features = [
        {
            imageSrc: "https://images.pexels.com/photos/221457/pexels-photo-221457.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=650&w=940",
            header: "Lorem ipsum",
            body: "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec sit amet pulvinar leo, a rutrum eros. Vestibulum a ultricies eros, vel bibendum quam. Praesent posuere laoreet est ut malesuada.",
            key: 1,
        },
        {
            imageSrc: "https://images.pexels.com/photos/1743231/pexels-photo-1743231.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=650&w=940",
            header: "Lorem ipsum",
            body: "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec sit amet pulvinar leo, a rutrum eros. Vestibulum a ultricies eros, vel bibendum quam. Praesent posuere laoreet est ut malesuada.",
            key: 2,
        },
        {
            imageSrc: "https://images.pexels.com/photos/374911/pexels-photo-374911.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500",
            header: "Lorem ipsum",
            body: "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec sit amet pulvinar leo, a rutrum eros. Vestibulum a ultricies eros, vel bibendum quam. Praesent posuere laoreet est ut malesuada.",
            key: 3,
        },
    ];

    return (
        <div>
            <div className={"container-fluid home-page-section-fluid"}>
                {features.map((feature, index) => {
                    return <div key={feature.key}><Feature flip={index & 1} feature={feature} /></div>;
                })}
            </div>
        </div>
    );
}

export default FeaturesSection;