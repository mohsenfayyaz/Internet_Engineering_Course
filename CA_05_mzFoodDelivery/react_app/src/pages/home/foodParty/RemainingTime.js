import React from "react";
import "./foodParty.css";

export default class RemainingTime extends React.Component {
    remainingTime;
    render() {
        return (
            <div className="remaining-time-box alert alert-info d-flex justify-content-center" role="alert">
                زمان باقیمانده:
                <span className="m-1"></span> {this.props.remainingTime}
            </div>
        );
    }
}