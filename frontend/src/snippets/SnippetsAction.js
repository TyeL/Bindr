import axios from 'axios';

export const FETCH_SNIPPETS_SUCCESS = 'FETCH_SNIPPETS_SUCCESS';


export const fetchSnippetsSuccess = snippets => ({
    type: FETCH_SNIPPETS_SUCCESS,
    payload: { snippets }
});

export function fetchSnippets(bindrId) {
    return (dispatch) => {
        return axios.get("http://localhost:8080/bindrs/" + bindrId + "/snippets")
            .then((res => {
                dispatch(fetchSnippetsSuccess(res.data));
            }));
    }
}
