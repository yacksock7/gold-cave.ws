import {action, flow, makeAutoObservable, observable} from "mobx";

const LogPrefix = '[AuthStore]';
export const LocalStorageTokenKey = '_BASIC_AUTHENTICATION_TOKEN_';

export const State = {
    Authenticated: 'Authenticated',
    NotAuthenticated: 'NotAuthenticated',
    Pending: 'Pending',
    Failed: 'Failed',
};

const EmptyLogin = {
    email: '',
    password: '',
};

const EmptyUser = {
    id: '',
    email: '',
    password : '',
    nickname: '',
    type: '',
};

export default class AuthStore {
    constructor(props) {
        this.authRepository = props.authRepository;

        makeAutoObservable(this);
    }

    // *doLogin(loginCallback) {
    //     this.loginState = State.Pending;
    //
    //     try {
    //         const param = this.login;
    //         const response = yield this.authRepository.signIn(param);
    //
    //         console.log("response : ", response);
    //
    //         if (response.user.authType === UserAuthType.AUTHENTICATED) {
    //             sessionStorage.setItem(AuthTokenStorageKey, response.token);
    //             this.loginState = State.Authenticated;
    //             this.loginUser = response.user;
    //             loginCallback()
    //         } else {
    //             this.loginState = State.NotAuthenticated;
    //             this.loginUser = response.user;
    //             this.changeEmailAuth(false);
    //         }
    //     } catch (e) {
    //         this.loginState = State.Failed;
    //         this.loginToken = '';
    //         this.loginUser = Object.assign({}, EmptyUser);
    //     }
    // }
    //
    // *checkLogin() {
    //     const token = sessionStorage.getItem(LocalStorageTokenKey);
    //
    //     if(token) {
    //         try {
    //             const user = yield this.authRepository.signCheck();
    //             this.loginState = State.Authenticated;
    //             this.loginUser = user;
    //         } catch(e) {
    //             this.loginState = State.NotAuthenticated;
    //             this.loginUser = Object.assign({}, EmptyUser);
    //         }
    //     }
    // }
    //
    // *doLogout() {
    //     sessionStorage.removeItem(LocalStorageTokenKey);
    //
    //     try {
    //         yield this.authRepository.signOut();
    //
    //         this.login = Object.assign({}, EmptyLogin);
    //         this.loginState = State.NotAuthenticated;
    //         this.loginUser = Object.assign({}, EmptyUser);
    //     } catch(e) {
    //         this.login = Object.assign({}, EmptyLogin);
    //         this.loginState = State.NotAuthenticated;
    //         this.loginUser = Object.assign({}, EmptyUser);
    //     }
    // }
}