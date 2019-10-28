import React, {Component} from 'react';
import {connect} from 'react-redux';
import {fetchSnippets} from "./SnippetsAction";

class Snippets extends Component {

    componentWillReceiveProps(nextProps) {
        if (this.props.bindrClicked !== nextProps.bindrClicked || this.props.id !== nextProps.id) {
            this.props.onFetchSnippets(nextProps.id);
        }
    }

    render() {
        const {snippets} = this.props;

        const snippetsSearchBar = (
            <div>
                <form className="mt-3">
                    <div className="form-row">
                        <div className="col-sm-8">
                            <input type="text" className="form-control" placeholder="Search"/>
                        </div>
                        <div className="col-sm-4">
                            <select id="inputState" className="form-control">
                                <option value="title">Title</option>
                                <option value="tags">Tags</option>
                                <option value="language">Language</option>
                            </select>
                        </div>
                    </div>
                </form>
            </div>
        );

        const snippetsToolbar = (
            <div className="snippets-toolbar col-sm-12 w-100 mt-3">
                <button type="button" className="btn btn-primary ml-2 btn-sm" data-toggle="tooltip"
                        data-placement="top" title="New Snippet">
                    <span className="fas fa-plus"/>
                </button>
                <button type="button" className="btn btn-primary ml-2 btn-sm" data-toggle="tooltip"
                        data-placement="top" title="Edit Snippet">
                    <span className="fas fa-pencil-alt"/>
                </button>
                <button type="button" className="btn btn-primary ml-2 btn-sm" data-toggle="tooltip"
                        data-placement="top" title="Delete Snippet"><
                    span className="fas fa-trash-alt"/>
                </button>
            </div>
        );

        const snippetsExists = (
            <ul className="nav flex-column flex-nowrap mt-4 snippets-list">
                {snippets.map(snippet =>
                    <li className="nav-item snippet-link mb-2 col-sm-12" key={snippet.id} >
                        <div className="row h-100 pl-3 pr-3 justify-content-sm-center d-flex align-items-sm-center">
                            <div className="col-sm-8">
                                <div className="row justify-content-sm-start">
                                    <div className="title">{snippet.title}</div>
                                </div>
                                <div className="row justify-content-sm-start">
                                    <div className="description">
                                        {snippet.description}
                                    </div>
                                </div>
                            </div>
                            <div className="col-sm-4">
                                <div className="row justify-content-sm-end">
                                    <div className="snippet-language">{snippet.language}</div>
                                </div>
                            </div>
                        </div>
                    </li>
                )}
            </ul>
        );


        const snippetsDontExist = (
            <div>
                <div className="row  h-100 mt-4 justify-content-sm-center align-items-sm-center">
                    <h4 className="noSnippets">No Snippets</h4>
                </div>
            </div>
        );

        if (snippets.length > 0) {
            return (
                <div>
                    {snippetsSearchBar}
                    {snippetsToolbar}
                    {snippetsExists}
                </div>
            )
        }

        return (
            <div>
                {snippetsSearchBar}
                {snippetsToolbar}
                {snippetsDontExist}
            </div>
        )
    }
}

const mapStateToProps = (state) => ({
    bindrs: state.bindrs.items,
    snippets: state.snippets.items,
    id: state.bindrs.id,
    bindrClicked: state.bindrs.bindrClicked,
});


const mapDispatchToProps = (dispatch) => ({
    onFetchSnippets: (bindrId) => dispatch(fetchSnippets(bindrId))
});


export default connect(mapStateToProps, mapDispatchToProps)(Snippets)