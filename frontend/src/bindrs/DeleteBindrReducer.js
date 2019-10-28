import {DELETE_BINDR_SUCCESS, DELETE_BINDR_ERRORS} from "./DeleteBindrActions";

const initialState = {
    deleteId: null,
    errors: []
};

export default function deleteBindrReducer(state = initialState, action) {
    switch (action.type) {
        case DELETE_BINDR_SUCCESS:
            return {
                ...state,
                deleteId: action.payload.deleteBindrId
            };
        case DELETE_BINDR_ERRORS:
            return {
                ...state,
                errors: action.payload.deletedBindrErrors
            };
        default:
            return state
    }
}