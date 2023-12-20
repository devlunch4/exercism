// use time::PrimitiveDateTime as DateTime;
//     use time::Duration;
// // Returns a DateTime one billion seconds after start.
// pub fn after(start: DateTime) -> DateTime {
//     let gigasecond = Duration::seconds(1000000000);
//     return start+gigasecond;
// }
use time::PrimitiveDateTime as DateTime;
use time::Duration;
// Returns a DateTime one billion seconds after start.
pub fn after(start: DateTime) -> DateTime {
    start.checked_add(Duration::seconds(1000000000 )).unwrap()
}