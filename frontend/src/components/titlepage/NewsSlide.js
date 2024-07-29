import React from "react";
import PropTypes from 'prop-types';

function NewsSlide({ title, content }) {
    return (
        <div className="news-slide">
            <h2>{title}</h2>
            <p>{content}</p>
        </div>
    );
}

NewsSlide.propTypes = {
    title: PropTypes.string.isRequired,
    content: PropTypes.string.isRequired
};

export default NewsSlide;
