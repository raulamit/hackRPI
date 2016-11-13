import React from 'react';
import axios from 'axios';

import Button from 'react-button';

//Generic Button component
class MouseButton extends React.Component {
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
            "@type": "MouseButtonEvent",
            "buttonDown": [this.props.buttonVal], 
            "buttonUp": []
        });
    }
              
    onButtonUp(){
        this.postToServer({
            "@type": "MouseButtonEvent",
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
export default MouseButton;
