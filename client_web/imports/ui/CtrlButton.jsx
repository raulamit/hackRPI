import React from 'react';
import axios from 'axios';

//import Button from 'react-bootstrap';
import Button from 'react-button';


function postToServer(event){
    axios({
        method: "post",
        url: this.props.targetURL,
        data: {
            "user": "ajsndkjaskdjbaksdba",
            "event": event,
        },
        headers: {
            "Content-Type": "application/json"
        },
    })
        .then(function (response) {
            console.log(response);
        })
        .catch(function (error) {
            console.log(error);
        });
}

function onButtonUp(event){
    this.postToServer(this.genDownPayload());
    killEvent(event);
}

function onButtonDown(event){
    this.postToServer(this.genUpPayload());
    killEvent(event);
}


function killEvent(event){
    event.preventDefault();
    event.stopPropogation();
    e.cancelBubble = true;
    e.returnValue = false;

    return false;
}

    /*
class HoldableButton extends React.Component {
    constructor(props){
        super(props);
    }
}
*/


class KeyButton extends React.Component {
    constructor(props) {
        super(props);

        this.onButtonDown = onButtonDown.bind(this);
        this.onButtonUp = onButtonUp.bind(this);
        this.postToServer = postToServer.bind(this); 
        this.genDownPayload = this.genDownPayload.bind(this);
        this.genUpPayload = this.genDownPayload.bind(this);
    }

    genDownPayload(){
        return ({
            "@type": "KeyboardEvent",
            "keyDown": [this.props.keyVal], 
            "keyUp": []
        });
    }

    genUpPayload(){
        return ({
            "@type": "KeyboardEvent",
            "keyDown": [], 
            "keyUp": [this.props.keyVal]
        });
    }

    render() {
        return (
            <div>
                <Button onActivate={this.onButtonDown} onDeactivate={this.onButtonUp}> {this.props.keyName}</Button>
            </div>
        );
    }
}

class MouseButton extends React.Component {
    constructor(props) {
        super(props);

        this.onButtonDown = onButtonDown.bind(this);
        this.onButtonUp = onButtonUp.bind(this);
        this.postToServer = postToServer.bind(this); 
        this.genDownPayload = this.genDownPayload.bind(this);
        this.genUpPayload = this.genDownPayload.bind(this);
    }

    genDownPayload(){
        return ({
            "@type": "MouseButtonEvent",
            "buttonDown": [this.props.buttonVal], 
            "buttonUp": []
        });
    }

    genUpPayload(){
        return ({
            "@type": "KeyboardEvent",
            "buttonDown": [], 
            "buttonUp": [this.props.buttonVal]
        });
    }
    render() {
        return (
            <div>
                <Button onActivate={this.onButtonDown} onDeactivate={this.onButtonUp}> {this.props.buttonName}</Button>
            </div>
        )}
}


export {KeyButton, MouseButton};
