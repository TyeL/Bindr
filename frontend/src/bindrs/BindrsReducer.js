import {
    FETCH_BINDRS_SUCCESS,
    GET_BINDR_ID_SUCCESS,
    BINDR_CLICKED,
    NEW_BINDR_BUTTON_CLICKED,
    EDIT_BINDR_BUTTON_CLICKED,
    DELETE_BINDR_BUTTON_CLICKED
} from "./BindrsActions";

const initialState = {
    items: [],
    id: null,
    bindrClicked: false,
    newBindrButtonClicked: false,
    editBindrButtonClicked: false,
    deleteBindrButtonClicked: false,
};

export default function bindrsReducer(state = initialState, action) {
    switch (action.type) {
        case FETCH_BINDRS_SUCCESS:
            return {
                ...state,
                items: action.payload.bindrs,
            };
        case GET_BINDR_ID_SUCCESS:
            return {
                ...state,
                id: action.payload.bindrId
            };
        case BINDR_CLICKED:
            return {
                ...state,
                bindrClicked: action.payload.clicked
            };
        case NEW_BINDR_BUTTON_CLICKED:
            return {
                ...state,
                newBindrButtonClicked: action.payload.newClicked
            };
        case EDIT_BINDR_BUTTON_CLICKED:
            return {
                ...state,
                editBindrButtonClicked: action.payload.editClicked
            };
        case DELETE_BINDR_BUTTON_CLICKED:
            return {
                ...state,
                deleteBindrButtonClicked: action.payload.deleteClicked
            };
        default:
            return state
    }
}

