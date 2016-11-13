import React from 'react';
import axios from 'axios';

import KeyButton from './KeyButton.jsx';
import MouseButton from './MouseButton.jsx';

class Controller extends React.Component {
    constructor(props) {
        super(props);
        this.renderInputs = this.renderInputs.bind(this);
        this.renderInput = this.renderInput.bind(this);
    }

    renderInputs() {
        if(this.props.inputs){
            return this.props.inputs.map(this.renderInput);
        }
        return [];
    }

    renderInput(input, index) {
        var elt;
        if(input["@type"] == "keybutton")
        {
            elt = (<KeyButton keyName={input.name} keyVal={input.value} targetURL={this.props.targetURL}/>);
        }
        else if(input["@type"] == "mousebutton")
        {
            elt = (<MouseButton buttonName={input.name} buttonVal={input.value} targetURL={this.props.targetURL}/>);
        }
        else
        {
            elt = (<p>Failed to make input</p>);
        }

        return <li key={index}>{elt}</li>
    }


    render() {

        return (
            <div className="container">
                <ul>
                {this.renderInputs()}
                </ul>
            </div>
        );
    }
}

export default Controller;
