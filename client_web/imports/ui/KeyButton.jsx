import React from 'react';
import axios from 'axios';

import Button from 'react-button';

//Generic Button component
class KeyButton extends React.Component {
    constructor(props) {
        super(props);

        this.onButtonDown = this.onButtonDown.bind(this);
        this.onButtonUp = this.onButtonUp.bind(this);
    }


    onButtonDown(){
        axios.post(this.props.targetURL, 
            {
                "@type": "KeyboardEvent",
                "buttonDown": [this.props.keyVal], 
                "buttonUp": []
            },
            {
                "headers": {
                    "Content-Type": "application/json"
                }
            }
        )
          .then(function (response) {
                  console.log(response);
                })
          .catch(function (error) {
                  console.log(error);
                });
    }
    
    onButtonUp(){
        axios.post(this.props.targetURL, {
            "@type": "KeyboardEvent",
            "buttonDown": [], 
            "buttonUp": [this.props.keyVal]
        })
          .then(function (response) {
                  console.log(response);
                })
          .catch(function (error) {
                  console.log(error);
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
