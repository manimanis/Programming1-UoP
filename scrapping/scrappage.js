// Part 1
// Obtain all units links
function removeSpaces(ch) {
    ch = ch.trim();
    return ch;
}

const links = [...document.querySelectorAll("a")].filter(link => {
    if (link.className == 'courseindex-link text-truncate') {
        const tc = removeSpaces(link.textContent);
        if (tc.startsWith('Learning Guide')) {
            return true;
        }
    }
    return false;
}).map(link => link.href);

console.log(JSON.stringify(links));