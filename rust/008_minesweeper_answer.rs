// Check if the specified position in the minefield contains a mine ('*')
fn is_mine(minefield: &[&str], row: usize, col: usize) -> bool {
    minefield
        .get(row)
        .and_then(|row| row.as_bytes().get(col))
        .map_or(false, |&b| b == b'*')
}

// Annotate a single square in the minefield based on the presence of mines
fn annotate_square(minefield: &[&str], row: usize, col: usize) -> char {
    if is_mine(minefield, row, col) {
        // If the square contains a mine, return '*'
        '*'
    } else {
        // Count the number of adjacent mines and annotate the square accordingly
        match (row.saturating_sub(1)..=row + 1)
            .flat_map(|r| (col.saturating_sub(1)..=col + 1).map(move |c| (r, c)))
            .filter(|&(r, c)| is_mine(minefield, r, c))
            .count()
        {
            0 => ' ',                // If no adjacent mines, annotate with space
            n => (b'0' + n as u8).into(), // Otherwise, annotate with the count of adjacent mines
        }
    }
} 

// Annotate an entire row of the minefield
fn annotate_row(minefield: &[&str], row: usize) -> String {
    (0..minefield[row].len())
        .map(|col| annotate_square(minefield, row, col))
        .collect()
}

// Annotate the entire minefield
pub fn annotate(minefield: &[&str]) -> Vec<String> {
    (0..minefield.len())
        .map(|row| annotate_row(minefield, row))
        .collect()
}