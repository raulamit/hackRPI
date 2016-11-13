import React, { Component } from 'react';
import axios from 'axios';

import Controller from './Controller.jsx';


// App component - represents the whole app
export default class App extends Component {
    constructor(props){
        super(props);

        //A bit hackish since this is top level. Should really be separate
        //Web wrapper around inputs and controller to handle web stuff
        this.state = {
            baseURL: "129.161.68.194:8090",
            controls: null,
            reqIndex: 0
        };

        this.onTextChange = this.onTextChange.bind(this);
    }

    //Handle change in ip box
    onTextChange(event) {
        var _baseURL = event.target.value;
        var thisReqIndex = this.state.reqIndex + 1;

        this.setState( {
            baseURL: _baseURL,
            reqIndex: thisReqIndex
        });

        axios.get('http://' + _baseURL + "/schema?app=arrow", {
            responseType: 'json' 
        })
            .then((response) => {
                this.setState({
                    "controls": response["data"]
                });
            })
            .catch((error) => {
                console.log(error);
                if(this.state.reqIndex == thisReqIndex)
                {
                    //this.setState({
                    //controls: null
                    //});
                }
            });
    }

    render() {
        var controls = this.state.controls;
        var controlsPortion;
        if(controls){
            controlsPortion = (
                <Controller inputs={controls["inputs"]} targetURL={"http://"+this.state.baseURL+"/event"} />
            );
        }
        else
        {
            controlsPortion = (
                <h2>No controller found at {this.state.baseURL}</h2>
            );
        }

        return (
            <div className="container">
                <header>
                    <h1>Remote Control for IP </h1>
                    <input type="text" value={this.state.baseURL} onChange={this.onTextChange} />
                </header>
                {controlsPortion}
            </div>
        );
    }
}




