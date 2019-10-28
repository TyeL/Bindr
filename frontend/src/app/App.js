import React, { Component } from 'react';
import '../assets/css/styles.css';
import Bindrs from '../bindrs/Bindrs';
import Snippets from '../snippets/Snippets';

class App extends Component {

  render() {

    return (
        <div className="container-fluid h-100 app">
            <div className="row flex-grow-1">
                <div className="col-sm-2 p-0 min-vh-100">
                    <div className="bindrs-sidebar p-2 min-vh-100">
                        <Bindrs/>
                    </div>
                </div>
                <div className="col-sm-2 p-0 min-vh-100">
                    <div className="snippets-middlebar p-2 min-vh-100">
                        <Snippets/>
                    </div>
                </div>
                <div className="col-sm-8 p-0 min-vh-100">
                    <div className="bindr-content p-2 min-vh-100">
                        content
                    </div>
                </div>
            </div>
            <div className="row"></div>
        </div>

    )
  }
}

export default App;
