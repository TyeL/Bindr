import {EDIT_BINDR_SUCCESS, EDIT_BINDR_ERRORS} from "./EditBindrAction";

const initialState = {
    item: {},
    errors: []
};

export default function editBindrReducer(state = initialState, action) {
    switch (action.type) {
        case EDIT_BINDR_SUCCESS:
            return {
                ...state,
                item: action.payload.bindr
            };
        case EDIT_BINDR_ERRORS:
            return {
                ...state,
                errors: action.payload.editBindrErrors
            };
        default:
            return state
    }
}