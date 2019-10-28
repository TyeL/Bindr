import axios from 'axios';

export const EDIT_BINDR_SUCCESS = 'EDIT_BINDR_SUCCESS';
export const EDIT_BINDR_ERRORS = 'EDIT_BINDR_ERRORS';

export const editBindrSuccess = (editdBindrId) => ({
    type: EDIT_BINDR_SUCCESS,
    payload: {editdBindrId}
});

export const editBindrErrors = (editBindrErrors) => ({
    type: EDIT_BINDR_ERRORS,
    payload: {editBindrErrors}
});

export function editBindr(bindrIdToBeEdited, item) {
    return (dispatch) => {
        return axios.put("http://localhost:8080/bindrs/" + bindrIdToBeEdited, item)
            .then(res => dispatch(editBindrSuccess(res.data)))
            .catch(err => {
                dispatch(editBindrErrors(err.response.data))
            })
    }
}