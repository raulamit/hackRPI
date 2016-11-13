import React from 'react';
import axios from 'axios';

import {KeyButton,MouseButton} from './CtrlButton.jsx';

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
        return (<div></div>);
    }

    renderInput(input, index) {
        var elt;
        if(input["@type"] == "keybutton")
        {
            elt = (<KeyButton keyName={input.name} keyVal={input.value} targetURL={this.props.targetURL} />);
        }
        else if(input["@type"] == "mousebutton")
        {
            elt = (<MouseButton buttonName={input.name} buttonVal={input.value} targetURL={this.props.targetURL} />);
        }
        else
        {
            elt = (<p>Failed to make input</p>);
        }

        return <li key={index}>{elt}</li>
    }


    render() {
        var inputs = this.renderInputs();
        var ret =  (
            <div className="container">
                <ul>
                {inputs}
                </ul>
            </div>
        );

        return ret;
    }
}

export default Controller;
