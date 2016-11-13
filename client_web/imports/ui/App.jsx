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
            baseURL: "129.161.68.194:8090"
        };

        this.onTextChange = this.onTextChange.bind(this);
        this.pullControls = this.pullControls.bind(this);
    }

    //Handle change in ip box
    onTextChange(event) {
        this.setState( {
            baseURL: event.target.value
        });
    }

    //Grab controls from baseURL
    pullControls() {
            /*
        axios.get(this.state.baseURL + "/controls", {)
            .then((response) => {
                return(response);
            })
            .catch((error) => {
                console.log(error);
                return null;
            });

        return null;
        */
        return (
            {
            "@data": "Controls",
            "inputs" : [
                {
                    "@type": "keybutton",
                    "name": "a",
                    "value": "1"
                },
                {
                    "@type": "keybutton",
                    "name": "b",
                    "value": "1"
                },
                {
                    "@type": "keybutton",
                    "name": "c",
                    "value": "1"
                }
            ]
        })
    }

    render() {
        var controls = this.pullControls();
        var controlsPortions;
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




