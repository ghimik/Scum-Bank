import React from 'react';
import PropTypes from 'prop-types';
import '../styles/FormInput.css'; 

function FormInput({ label, type, name, value, onChange, required }) {
    return (
        <div className="form-input">
            <label htmlFor={name}>{label}</label>
            <input
                type={type}
                id={name}
                name={name}
                value={value}
                onChange={onChange}
                required={required}
            />
        </div>
    );
}

FormInput.propTypes = {
    label: PropTypes.string.isRequired,
    type: PropTypes.string.isRequired,
    name: PropTypes.string.isRequired,
    value: PropTypes.string.isRequired,
    onChange: PropTypes.func.isRequired,
    required: PropTypes.bool
};

export default FormInput;
