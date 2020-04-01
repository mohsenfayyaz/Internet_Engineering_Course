import * as React from "react";
import "../../../Assets/styles/food-modal-styles.scss";
import {enToFaNumber} from "../../../utils/utils";
import UserService from "../../../services/UserService";
import Spinner from "react-bootstrap/Spinner";


export default class Food extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            userChosenEntity: 1,
            isLoading: false
        };
    }

    async addToCart() {
        this.setState({isLoading: true});
        let response = await UserService.addToCart(this.props.food.restaurantId, this.props.food.name, this.state.userChosenEntity);
        console.log("SSS");
        console.log(response);
        this.setState({isLoading: false});
    }

    incrementEntity() {
        if (this.state.userChosenEntity < this.props.food.count) {
            this.setState({
                userChosenEntity: this.state.userChosenEntity + 1
            })
        }
    }

    decrementEntity() {
        if (this.state.userChosenEntity > 1) {
            this.setState({
                userChosenEntity: this.state.userChosenEntity - 1
            })
        }
    }

    render() {
        return (
            <div>
                <div className="top-part">
                    <div className="restaurant-name">
                        {this.props.food.restaurantName}
                    </div>
                    <div className="food-wrapper">
                        <div className="food-img-wrapper">
                            <div className="food-img">
                                <img src={this.props.food.image} alt="temp"/>
                            </div>
                        </div>
                        <div className="food-detail-wrapper">
                            <div className="food-detail">
                                <div className="name-rate">
                                    <div className="food-name">
                                        {this.props.food.name}
                                    </div>
                                    <div className="food-rate">
                                        {enToFaNumber(this.props.food.popularity * 5)}
                                        <span className="star-icon">⋆</span>
                                    </div>
                                </div>
                                <div className="food-description">
                                    {this.props.food.description}
                                </div>
                                <div className="food-prices">
                                    <div className="old-price">
                                        {enToFaNumber(this.props.food.oldPrice)}
                                    </div>
                                    <div className="curr-price">
                                        {enToFaNumber(this.props.food.price)} تومان
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>


                <div className="separator-border"/>


                <div className="bottom-part">
                    <div className="remaining">
                        <span> موجودی : </span>
                        <span> {enToFaNumber(this.props.food.count)} </span>
                    </div>
                    <div className="actions">
                        <div className="inc-amount mx-1" onClick={() => this.incrementEntity()}>
                            <i className="flaticon-plus"/>
                        </div>
                        <div className="amount mx-1">
                            {enToFaNumber(this.state.userChosenEntity)}
                        </div>
                        <div className="dec-amount mx-1" onClick={() => this.decrementEntity()}>
                            <i className="flaticon-minus"/>
                        </div>
                        <div className="add-to-cart mx-2"
                             onClick={() => (!this.state.isLoading ? this.addToCart() : {/*pass*/})}>
                            <span>
                                افزودن به سبد خرید
                            </span>
                            {this.state.isLoading &&
                            <span className="spinner-border spinner-border-sm m-1" role="status" aria-hidden="true"/>
                            }
                        </div>
                    </div>
                </div>
            </div>
        );
    }
}