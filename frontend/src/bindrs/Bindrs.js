import React, {Component} from 'react';
import {connect} from 'react-redux';
import {fetchBindrs, getClickedBindrId, setBindrClickBoolean} from "./BindrsActions";

class Bindrs extends Component {
    constructor(props) {
        super(props);

        this.handleBindrClick = this.handleBindrClick.bind(this);
    }

    componentDidMount() {
        this.props.onFetchBindrs();
    }

    handleBindrClick = (e, bindr) => {
        const id = bindr.id;

        this.props.onGetClickedBindrId(id);
        if (id != null) {
            this.props.onSetBindrClickBoolean(true);
        }
    };

    render() {

        const {bindrs} = this.props;

        const bindrsToolbar = (
            <div>
                <h2 className="col-sm-12 w-100 brand mt-3">Bindrs</h2>
                <div className="bindrs-toolbar col-sm-12 w-100 mt-3">
                    <button type="button" className="btn btn-primary ml-2 btn-sm" data-toggle="tooltip"
                            data-placement="top" title="New Bindr">
                        <span className="fas fa-plus"/>
                    </button>
                    <button type="button" className="btn btn-primary ml-2 btn-sm" data-toggle="tooltip"
                            data-placement="top" title="Edit Bindr">
                        <span className="fas fa-pencil-alt"/>
                    </button>
                    <button type="button" className="btn btn-primary ml-2 btn-sm" data-toggle="tooltip"
                            data-placement="top" title="Delete Bindr"><
                        span className="fas fa-trash-alt"/>
                    </button>
                </div>
            </div>
        );

        const bindrsExists = (

            <ul className="nav flex-column flex-nowrap mt-4 bindrs-list">
                {bindrs.map((bindr, index) =>
                    <li className="nav-item bindr-link mb-2 col-sm-12" key={bindr.id}
                        onClick={((e) => this.handleBindrClick(e, bindr))}
                    style={this.props.id === bindr.id ? {backgroundColor: '#0d547b'} : null }>
                        <div className="row h-100 pl-3 pr-3 justify-content-sm-center d-flex align-items-sm-center">
                            <div className="col-sm-9">
                                <div className="row justify-content-sm-start">
                                    <div className="title">{bindr.title}</div>
                                </div>
                                <div className="row justify-content-sm-start">
                                    <div className="description">
                                        {bindr.description}
                                    </div>
                                </div>
                            </div>
                            <div className="col-sm-3">
                                <div className="row justify-content-sm-end">
                                    <div className="amount" style={this.props.id === bindr.id ? {backgroundColor: '#12678e'} : null }>{bindr.num}</div>
                                </div>
                            </div>
                        </div>
                    </li>
                )}
            </ul>
        );

        const bindrsDontExist = (
            <div>
                <div className="row  h-100 mt-4 justify-content-sm-center align-items-sm-center">
                    <h4 className="noBindrs">No Bindrs</h4>
                </div>
            </div>
        );

        if (bindrs.length > 0) {
            return (
                <div>
                    {bindrsToolbar}
                    {bindrsExists}
                </div>
            )
        }

        return (
            <div>
                {bindrsToolbar}
                {bindrsDontExist}
            </div>
        )

    }
}

const mapStateToProps = (state) => ({
    bindrs: state.bindrs.items,
    id: state.bindrs.id,
    bindrClicked: state.bindrs.bindrClicked
});

const mapDispatchToProps = (dispatch) => ({
    onFetchBindrs: () => dispatch(fetchBindrs()),
    onGetClickedBindrId: (id) => dispatch(getClickedBindrId(id)),
    onSetBindrClickBoolean: (clickedState) => dispatch(setBindrClickBoolean(clickedState)),
});

export default connect(mapStateToProps, mapDispatchToProps)(Bindrs)