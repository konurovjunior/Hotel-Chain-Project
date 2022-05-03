import React, { useState } from 'react';

import './Search.css';

import SearchForm from './SearchForm';
import Results from './Results';

function SearchPage() {
    const [results, setResults] = useState([]);

    return (
        <div className={"search-page-container mx-auto mt-4"}>

            <div className={"container-fluid"}>
                <SearchForm onResultsLoad={data => setResults(data)} />
            </div>

            <div className={"container-fluid"}>
                <Results results={results} />
            </div>

        </div>
    );
}

export default SearchPage;