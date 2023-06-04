import React from "react";
import {Routes, Route} from "react-router-dom";
import {withStyles} from "@material-ui/core/styles";
import {CssBaseline} from "@material-ui/core";
import Home from "./views/Home";


const style = () => ({
    root: {
        display: 'flex',
    }
});

class App extends React.Component {
    constructor(props) {
        super(props);
    }
    render() {
        const {classes} = this.props;
        return (
            <div className={classes.root}>
                <Routes>
                    <Route path="*" element={<Home />}/>
                </Routes>
            </div>
        );
    }
}

export default withStyles(style) (App);
