
import "../../Assets/styles/login-styles.css";
import * as React from "react";
import {Link} from "react-router-dom";
import AuthService from "../../services/AuthService";
import GoogleBtn from "../general/googleBtn";
import Version from "../general/Version";
import {toast} from "react-toastify";

export default class Login extends React.Component {

    constructor(props) {
        super(props);
        this.state = {
            email: "",
            password: "",
            isLoading: false
        };
        this.handleChange = this.handleChange.bind(this);
        this.handleSubmit = this.handleSubmit.bind(this);
        this.validateForm = this.validateForm.bind(this);
    }

    componentDidMount() {
        document.title = "Sign in - MzFood Accounts";
    }


    handleChange(event) {
        const target = event.target;
        const value = target.value;
        const name = target.name;
        this.setState({ [name]: value });
    }

    handleSubmit(event) {
        this.setState({
            isLoading: true
        });
        event.preventDefault();
        let userForm = {
            email: this.state.email,
            password: this.state.password
        };

        AuthService.login(userForm).then(data => {
            toast.success("ورود با موفقیت انجام شد");
            this.setState({
                isLoading: false
            });
            console.log(data.data);
            let bearerToken = data.data;
            let token = bearerToken.slice(7, bearerToken.length);
            console.log(token);
            localStorage.setItem("token", token);
            window.location = "/";
        }).catch(e => {
            this.setState({
                isLoading: false
            });
            toast.error(e.response.data.message);
        })
    }

    validateForm() {
        return (
            this.state.email.length > 0 &&
            this.state.password.length > 0
        );
    }

    render() {
        return (
            <div className="main-box w-100 h-100">
                <div className="d-flex flex-row-reverse no-gutters h-100">
                    <div className="col-lg-9 col-md-8 h-100">
                        <img className="background-img" src={require("../../Assets/images/food.jpg")} alt="" />
                    </div>
                    <div className="col-lg-3 col-md-4 overflow-auto">
                        <div className="form-container align-items-center">
                            <div className="d-flex">
                                <h1>
                                    ورود
                                </h1>
                            </div>
                            <div className="d-flex justify-content-center align-items-center ">
                                <form action="tmp" className=" w-100" onChange={this.handleChange} onSubmit={this.handleSubmit}>
                                    <div className="">
                                        <div className="form-item">
                                            <div className="d-flex">
                                                <label htmlFor="email">
                                                    ایمیل
                                                </label>
                                            </div>
                                            <input type="text" name="email" id="email" placeholder="ایمیل" />
                                        </div>
                                        <div className="form-item">
                                            <div className="d-flex">
                                                <label htmlFor="password">
                                                    رمز عبور
                                                </label>
                                            </div>
                                            <input type="password" name="password" id="password"
                                                   placeholder="***********" />
                                        </div>

                                        <div className="form-submit row">
                                            <div className="col-auto">
                                                <input type="submit" disabled={!this.validateForm()} value="ورود" />
                                                {this.state.isLoading &&
                                                <span className="spinner-border mr-2" role="status" aria-hidden="true"/>
                                                }
                                            </div>
                                            <div
                                                className="col-auto d-flex justify-content-center align-items-center clickable">
                                                <div className="login-arrow">
                                                    <i className="flaticon-arrow"/>
                                                </div>
                                                <span>
                                                    <Link to="/signup">
                                                        ثبت‌نام
                                                    </Link>
                                                </span>
                                            </div>
                                        </div>
                                        <GoogleBtn />
                                        <Version />
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        );
    }

}