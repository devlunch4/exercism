pub fn reverse(input: &str) -> String {
   // todo!("Write a function to reverse {input}");
   input.chars().rev().collect()
}


// //new version
// //You will need to use external libraries (a crate in rust lingo) for the bonus task.
// //A good place to look for those is crates.io, the official repository of crates.
// use unicode_segmentation::UnicodeSegmentation;
// pub fn reverse(input: &str) -> String {
//     input.graphemes(true).rev().collect()
// }
