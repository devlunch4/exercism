use std::collections::HashSet;

pub fn anagrams_for<'a>(word: &str, possible_anagrams: &[&'a str]) -> HashSet<&'a str> {
    let word_lower = word.to_lowercase();
    let word_sorted = sort_chars(&word_lower);

    possible_anagrams
        .iter()
        .filter(|candidate| {
            let candidate_lower = candidate.to_lowercase();
            candidate_lower != word_lower
                && candidate_lower.len() == word_lower.len()
                && sort_chars(&candidate_lower) == word_sorted
        })
        .copied()
        .collect()
}

// Function to convert a string to a sorted vector of characters
fn sort_chars(word: &str) -> Vec<char> {
    let mut chars: Vec<char> = word.chars().collect();
    chars.sort_unstable();
    chars
}




[package]
edition = "2021"
name = "anagram"
version = "0.0.0"