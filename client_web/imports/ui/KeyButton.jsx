import React from 'react';
import axios from 'axios';

import Button from 'react-button';

//Generic Button component
class KeyButton extends React.Component {
    constructor(props) {
        super(props);

        this.onButtonDown = this.onButtonDown.bind(this);
        this.onButtonUp = this.onButtonUp.bind(this);
        this.postToServer = this.postToServer.bind(this); 
    }

    postToServer(event){
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

    onButtonDown(){
        this.postToServer({
                "@type": "KeyboardEvent",
                "keyDown": [this.props.keyVal], 
                "keyUp": []
            });
    }
    
    onButtonUp(){
        this.postToServer({
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
export default KeyButton;
