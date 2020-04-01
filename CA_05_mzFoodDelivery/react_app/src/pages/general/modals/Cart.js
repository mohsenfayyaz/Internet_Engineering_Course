import * as React from "react";
import "../../../Assets/styles/cart-styles.css";
import UserService from "../../../services/UserService";
import {enToFaNumber} from "../../../utils/utils";
import {toast} from "react-toastify";
import CartItem from "./CartItem";


export default class Cart extends React.Component {

    constructor(props) {
        super(props);
        this.state = {
            cart: {}
        }
    }


    componentDidMount() {
        this.getUserCart();
    }

    getUserCart () {
        UserService.getCart().then(cart => {
            this.setState({cart: cart.data});
            console.log(this.state.cart);
        })
    }


    render() {
        return (
            <div className="cart-wrapper">
                <div className="cart-container">
                    {this.state.cart && this.state.cart.cartItems && (
                        <span className="cart-title">
                            سبد خرید
                        </span>
                    ) }
                    {this.getCart()}
                </div>
            </div>
        );
    }


    getCart() {
        if (this.state.cart && this.state.cart.cartItems)
        return (
        <>
            <div className="cart-list-wrapper d-flex justify-content-center">
                        {this.state.cart.cartItems.map(cartItem => {
                            return (
                             <CartItem cartItem={cartItem}/>
                            )
                        })}

            </div>
            <div className="total-price">
                جمع کل:
                <span>
                    {enToFaNumber(this.state.cart.totalPrice || 0)}
                تومان
                            </span>
            </div>

            <button type="button" className="btn btn-default btn-cyan">
                تایید نهایی
            </button>

        </>
        );
        else
            return (
            <div className="spinner-grow text-danger food-party-loading-box align-self-center" role="status">
                <span className="sr-only">Loading...</span>
            </div>
            )
    }

}


