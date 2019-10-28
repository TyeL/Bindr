import axios from 'axios';
export const FETCH_BINDRS_SUCCESS = 'FETCH_BINDRS_SUCCESS';
export const GET_BINDR_ID_SUCCESS = 'GET_BINDR_ID_SUCCESS';
export const BINDR_CLICKED = 'BINDR_CLICKED';
export const NEW_BINDR_BUTTON_CLICKED = 'NEW_BINDR_BUTTON_CLICKED';
export const EDIT_BINDR_BUTTON_CLICKED = 'EDIT_BINDR_BUTTON_CLICKED';
export const DELETE_BINDR_BUTTON_CLICKED = 'DELETE_BINDR_BUTTON_CLICKED';

export const fetchBindrsSuccess = (bindrs) => ({
   type: FETCH_BINDRS_SUCCESS,
   payload: { bindrs }
});

export function fetchBindrs() {
    return (dispatch) => {

        return axios.get("http://localhost:8080/bindrs")
            .then((res => {
                dispatch(fetchBindrsSuccess(res.data))
                console.log(res)
            }));
    }
}

export const getBindrIdSuccess = (bindrId) => ({
    type: GET_BINDR_ID_SUCCESS,
    payload: { bindrId }
});

export function getClickedBindrId(id) {
    return (dispatch) => {
        dispatch(getBindrIdSuccess(id))
    }
}

export const bindrClicked = clicked => ({
    type: BINDR_CLICKED,
    payload: { clicked }
});

export function setBindrClickBoolean(isClicked) {
    return (dispatch) => {
        dispatch(bindrClicked(isClicked))
    }
}

export const newBindrButtonClicked = newClicked => ({
    type: NEW_BINDR_BUTTON_CLICKED,
    payload: { newClicked }
});

export function newBindr(isNewClicked) {
    return (dispatch) => {
        dispatch(newBindrButtonClicked(isNewClicked))
    }
}

export const editBindrButtonClicked = editClicked => ({
    type: EDIT_BINDR_BUTTON_CLICKED,
    payload: { editClicked }
});

export function editBindr(isEditClicked) {
    return (dispatch) => {
        dispatch(editBindrButtonClicked(isEditClicked))
    }
}

export const deleteBindrButtonClicked = deleteClicked => ({
    type: DELETE_BINDR_BUTTON_CLICKED,
    payload: { deleteClicked }
});

export function deleteBindr(isDeleteClicked) {
    return (dispatch) => {
        dispatch(deleteBindrButtonClicked(isDeleteClicked))
    }
}
