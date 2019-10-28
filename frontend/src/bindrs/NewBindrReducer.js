import {NEW_BINDR_SUCCESS, NEW_BINDR_ERRORS} from "./NewBindrAction";

const initialState = {
    item: {},
    errors: []
};

export default function newBindrReducer(state = initialState, action) {
    switch (action.type) {
        case NEW_BINDR_SUCCESS:
            return {
                ...state,
                item: action.payload.bindr
            };
        case NEW_BINDR_ERRORS:
            return {
                ...state,
                errors: action.payload.newBindrErrors
            };
        default:
            return state
    }
}