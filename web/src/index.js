import React from 'react';
import ReactDOM from 'react-dom/client';
import './index.css';
import App from './App';
import * as serviceWorker from './serviceWorker';

import {configure} from "mobx";
import {Provider as MobxProvider} from "mobx-react";
import {ThemeProvider} from "@mui/material";
import {stores} from "./stores";
import configureTheme from "./configureTheme";
import {BrowserRouter} from "react-router-dom";
import {serverContextPath} from "./AppConstants";


configure({enforceActions: "always"});

const theme = configureTheme();

const root = document.getElementById('root');

ReactDOM.createRoot(root).render(
    <BrowserRouter basename={serverContextPath}>
        <MobxProvider {...stores}>
            <ThemeProvider theme={theme}>
                <App />
            </ThemeProvider>
        </MobxProvider>
    </BrowserRouter>
);

// If you want your app to work offline and load faster, you can change
// unregister() to register() below. Note this comes with some pitfalls.
// Learn more about service workers: https://bit.ly/CRA-PWA
serviceWorker.unregister();
