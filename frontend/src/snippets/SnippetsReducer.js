import {FETCH_SNIPPETS_SUCCESS} from "./SnippetsAction";

const initialState = {
    items: [],
    item: {},
};

export default function snippetsReducer(state = initialState, action) {
    switch (action.type) {
        case FETCH_SNIPPETS_SUCCESS:
            return {
                ...state,
                items: action.payload.snippets
            };
        default:
            return state
    }
}
